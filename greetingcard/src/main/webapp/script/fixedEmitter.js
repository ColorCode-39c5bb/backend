import Emitter from './emitter.js';

const emitter = new Emitter(10);
emitter.position = [window.innerWidth/2, window.innerHeight];
emitter.initialVelocity = [15, 15];
emitter.maxInitVelocity = 20;
emitter.interval = 200;

emitter.launch(function(missile){ // animate
	missile.position[0] += missile.velocity[0];
    missile.position[1] += missile.velocity[1];
    missile.element.style.left = `${missile.position[0]}px`;
    missile.element.style.top = `${missile.position[1]}px`;
    missile.velocity[0] += missile.acceleration[0];
    missile.velocity[1] += missile.acceleration[1];
});

const maxHeightRatio = 2/3;
// 定时发射小球
setInterval(() => {
    emitter.add((function(missile){ // initMissile
		missile.element.textContent = Emitter.missileHub[Math.floor(Math.random() * Emitter.missileHub.length)];
		missile.element.style.color = `hsl(${Math.random()*360}, 100%, 50%)`;
		missile.element.style.fontSize = `${Math.random()*20 + 10}px`;
		missile.element.style.zIndex = Math.random() > 0.5 ? 1 : -1;

		missile.position[0] = this.position[0];
		missile.position[1] = this.position[1];
		missile.element.style.left = `${missile.position[0]}px`;
		missile.element.style.top = `${missile.position[1]}px`;

		missile.velocity[0] = Math.random()*2*this.maxInitVelocity - this.maxInitVelocity;
		missile.velocity[1] = Math.max(-Math.random() * window.innerHeight / 2, -this.maxInitVelocity);
		const maxAcc = missile.velocity[1]**2 / (2 * window.innerHeight*maxHeightRatio);
		missile.acceleration = [0, Math.max(Math.random() * 2 * maxAcc, maxAcc)];

		document.body.appendChild(missile.element);
		missile.element.style.display = "block";
    }).bind(emitter)
);}, emitter.interval);

window.addEventListener("resize", () => {
    emitter.position[0] = window.innerWidth / 2;
    emitter.position[1] = window.innerHeight;
});
