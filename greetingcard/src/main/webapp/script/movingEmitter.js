import Emitter from "./emitter.js";

const emitter = new Emitter(20);
emitter.position=[0,0];
emitter.initialVelocity = [0, -10];
emitter.maxInitVelocity = [5, 0];
emitter.interval = 75;

const 弹射加速度 = 0.75;
emitter.launch(function(missile){
	if(missile.velocity[1]>=0 && missile.acceleration[1]>=弹射加速度) missile.acceleration[1] = 0.2 + 0.2*(Math.random()-0.5);
	// 更新位置
	missile.position[0] += missile.velocity[0];
	missile.position[1] += missile.velocity[1];
	missile.element.style.left = `${missile.position[0]}px`;
	missile.element.style.top = `${missile.position[1]}px`;
	missile.velocity[0] += missile.acceleration[0];
	missile.velocity[1] += missile.acceleration[1];
	// 更新旋转
	missile.element.style.transform = `rotate(${missile.position[1]*0.1}deg)`;
	// 更新缩放（掉落时稍微缩小）
	missile.element.style.transform += ` scale(${Math.max(0.5, 1 - (missile.position[1] / window.innerHeight) * 0.3)})`;	
	// 更新透明度
	missile.element.style.opacity = 1-missile.position[1]/window.innerHeight;
});

let lastMoveTime = 0;
document.addEventListener('mousemove', (e)=>{
    const now = Date.now();
    if (now-lastMoveTime < emitter.interval) return;
	
	emitter.position[0] = e.clientX;
    emitter.position[1] = e.clientY;
	emitter.add((function(missile){
		missile.element.textContent = Emitter.missileHub[Math.floor(Math.random() * Emitter.missileHub.length)];
		missile.element.style.color = `hsl(${Math.random()*360}, 100%, 50%)`;
		missile.element.style.fontSize = `${Math.random()*20 + 10}px`;
		missile.element.style.zIndex = Math.random() > 0.5 ? 1 : -1;
		// missile.element.style.filter = `hue-rotate(${Math.random()*60+280}deg) drop-shadow(0 0 5px rgba(255,255,255,0.5))`; // 紫色到粉色范围

		missile.position[0] = this.position[0];
		missile.position[1] = this.position[1];
		missile.element.style.left = `${missile.position[0]}px`;
		missile.element.style.top = `${missile.position[1]}px`;
		missile.acceleration[1] = 弹射加速度;
		missile.velocity[0] = (Math.random()-0.5) * this.maxInitVelocity[0];
		missile.velocity[1] = this.initialVelocity[1];
		document.body.appendChild(missile.element);
		missile.element.style.display = "block";
	}).bind(emitter));
	lastMoveTime = now;
})