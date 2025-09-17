package cn.violetgarden.game.json;

public class Action {
    String type;
    Player opst;
    Player self;
    Object extra;

    
    public Action() {
    }
    
    public Action(String type, Player opst, Player self, Object extra) {
        this.type = type;
        this.opst = opst;
        this.self = self;
        this.extra = extra;
    }

    
    public Player getOpst() {
        return opst;
    }
    public void setOpst(Player opst) {
        this.opst = opst;
    }
    public Player getSelf() {
        return self;
    }
    public void setSelf(Player self) {
        this.self = self;
    }
    @Override
    public String toString() {
        return "Action [type=" + type + ", opst=" + opst + ", self=" + self + ", extra=" + extra + "]";
    }
    public Object getExtra() {
        return extra;
    }
    public void setExtra(Object extra) {
        this.extra = extra;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}