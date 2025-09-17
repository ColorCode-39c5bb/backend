package cn.violetgarden.game;

import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.violetgarden.game.json.Action;
import cn.violetgarden.game.json.Player;
import cn.violetgarden.game.json.conpoment.Card;
import cn.violetgarden.game.json.conpoment.Slot;

@ServerEndpoint("/server")
public class WSServer {
    static ObjectMapper objectMapper = new ObjectMapper();
    static Random random = new Random();

    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());

    @OnOpen
    public void onOpen(Session session) {
        session.getUserProperties().put("name", session.getRequestParameterMap().get("name").get(0));
        
        sessions.add(session);
        System.out.println("连接: " + session.getId());
        // session.getAsyncRemote().sendText(session.getId()); // 向新连接发送欢迎消息
    }

    static ListIterator<Card> cards_red = Card.cards_red.listIterator(),
                        cards_black = Card.cards_black.listIterator();
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        Action action = objectMapper.readValue(message, Action.class);
        Player tempPlayer;
        String handCount;
        System.out.println("Receive:" + action);
        switch (action.getType()){
            case "inviting": //invite
                System.out.println("INVITE");
                // action.setExtra() = opst.name
                for (Session s : sessions) {
                    if (s.getUserProperties().get("name").equals(action.getExtra())) {
                        try {
                            action.setExtra(s.getUserProperties().get("name"));
                            action.setType("invited");
                            s.getBasicRemote().sendText(objectMapper.writeValueAsString(action));
                            session.getUserProperties().put("opst", action.getExtra());
                            System.out.println("invite sent: " + action.getExtra());
                        } catch (IOException e) {
                            System.err.println("invite sent failed: " + e.getMessage());
                        }
                    }
                }
                break;
            case "reply"://reply for inviting
                for (Session s : sessions) {
                    if (s.getUserProperties().get("opst")!=null &&
                        s.getUserProperties().get("opst").equals(session.getUserProperties().get("name"))
                    ) {
                        if ("refuse".equals(action.getExtra())) break;
                        else if("accept".equals(action.getExtra()))
                            try {
                                s.getUserProperties().put("opst", session);
                                session.getUserProperties().put("opst", s);
                                action.setExtra(session.getUserProperties().get("name"));
                                s.getBasicRemote().sendText(objectMapper.writeValueAsString(action));
                                System.out.println("invited successed: " + action.getExtra());
                            } catch (IOException e) {
                                System.err.println("invited failed: " + e.getMessage());
                            }
                        break;
                    }
                }
                break;



                
            case "deal":
                System.out.println("DEAL");
                boolean camp = random.nextBoolean();
                action.setOpst(new Player(action.getOpst().getSlots(), new ArrayList<>() , camp,  camp));
                action.setSelf(new Player(action.getSelf().getSlots(), new ArrayList<>() ,!camp, !camp));
                for(Slot slot:action.getOpst().getSlots()) slot.setCountAttackable(1);
                for(Slot slot:action.getSelf().getSlots()) slot.setCountAttackable(1);
                action.setType("deal");
                
                Card back4 = new Card("b", "4");
                action.getOpst().getCards().add(back4);
                deal(action.getSelf(), 4);
                session.getBasicRemote().sendText(objectMapper.writeValueAsString(action));
                
                action.getSelf().getCards().clear(); action.getSelf().getCards().add(back4);
                tempPlayer=action.getOpst(); action.setOpst(action.getSelf()); action.setSelf(tempPlayer);

                action.getSelf().getCards().clear();
                deal(action.getSelf(), 4);
                ((Session)session.getUserProperties().get("opst")).getBasicRemote().sendText(objectMapper.writeValueAsString(action));
                break;
            case "attack":
                System.out.println("ATTACK");
                List<Slot> slotAttacking = new ArrayList<Slot>(), slotAttacked = new ArrayList<Slot>();
                for(Slot slot:action.getSelf().getSlots()) if(slot.isSelected()){slotAttacking.add(slot); slot.setSelected(false);};
                for(Slot slot:action.getOpst().getSlots()) if(slot.isSelected()){slotAttacked.add(slot); slot.setSelected(false);};
                int[] sumActionAttack={0}, sumActionDefence={0};
                slotAttacking.forEach(slot->sumActionAttack[0]+=slot.sumSlotAttack());
                slotAttacked.forEach(slot->sumActionDefence[0]+=slot.sumSlotDefence());
                System.out.println("Damage: " + sumActionAttack[0]);
                System.out.println("Defence: " + sumActionDefence[0]);
                if (sumActionAttack[0] < sumActionDefence[0]) break;

                slotAttacked.get(0).setCards(new Card[0]);
                slotAttacking.forEach(s->s.setCountAttackable(s.getCountAttackable()-1));
                action.setType("update");
                session.getBasicRemote().sendText(objectMapper.writeValueAsString(action));
                
                handCount = Integer.toString(action.getOpst().getCards().size());
                action.getSelf().getCards().clear(); action.getSelf().getCards().add(new Card("b", handCount));
                tempPlayer = action.getSelf(); action.setSelf(action.getOpst()); action.setOpst(tempPlayer);
                
                action.setOpst(null);
                ((Session)session.getUserProperties().get("opst")).getBasicRemote().sendText(objectMapper.writeValueAsString(action));
                break;
            case "update":
                System.out.println("UPDATE");

                handCount = Integer.toString(action.getSelf().getCards().size());
                action.getSelf().getCards().clear(); action.getSelf().getCards().add(new Card("b", handCount));
                action.setOpst(action.getSelf()); action.setSelf(null);

                ((Session) session.getUserProperties().get("opst")).getBasicRemote().sendText(objectMapper.writeValueAsString(action));
                break;
            case "turn":
                System.out.println("TURN");
                tempPlayer = action.getOpst();

                action.getSelf().setActionable(false);
                action.setOpst(null);
                action.setType("update"); action.setExtra("turn");
                session.getBasicRemote().sendText(objectMapper.writeValueAsString(action));

                action.setSelf(tempPlayer);

                action.getSelf().setActionable(true);
                for(Slot slot:action.getSelf().getSlots()) slot.setCountAttackable(1);
                action.getSelf().getCards().clear(); 
                deal(action.getSelf(), 2);
                action.setType("turn");
                ((Session) session.getUserProperties().get("opst")).getBasicRemote().sendText(objectMapper.writeValueAsString(action));
                break;
            default :
                break;
        }


    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
        System.out.println("Disconnected: " + session.getId());
    }

    @OnError
    public void onError(Session session, Throwable error) {
        System.err.println("发生错误: " + error.getMessage());
    }

    private void deal(Player p, int countCardDeal){
        ListIterator<Card> cards = p.isCamp()==Player.CAMP_RED?cards_red:cards_black;
        for(int i=0; i<countCardDeal; i++) p.getCards().add(cards.next());
    }
}
