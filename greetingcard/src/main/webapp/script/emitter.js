export default function Emitter(maxExistingCount=10, Constuctor=Missile) {
	this.pool = new Pool(Constuctor, maxExistingCount);
	this.position = [0, 0];
	this.initialVelocity = [0, -10];
	this.maxInitVelocity = [5, 0];
	this.interval = 75;
}
Emitter.missileHub = ["🎂", "🎁", "🎉", "🍰", "🍭",
	"H", "A", "P", "P", "Y", "B", "I", "R", "T", "H", "D", "A", "Y",
	"生", "日", "快", "乐"];
Emitter.prototype.add = function(initMissile){
    const missile = this.pool.get();
	if(!missile) return;

	initMissile(missile);
};
Emitter.prototype.launch = function(animate){
    function launchAnimate(){
        for(const missile of this.pool.poolActive) {
            // 边界检测
            if(missile.element.offsetTop < 0 || missile.element.offsetTop > window.innerHeight ||
				missile.element.offsetLeft > window.innerWidth || missile.element.offsetLeft < 0
			) {
                this.pool.release(missile);
                continue;
            }
            animate(missile);
        }
        requestAnimationFrame(launchAnimate.bind(this));
    };
    launchAnimate.call(this);
};


function Pool(Constuctor, maxExistingCount=10){
    this.poolAvailable = []; 
    this.poolActive = [];    
    for (let i = 0; i < maxExistingCount; i++) {
        this.poolAvailable.push(new Constuctor()); // Java毕生无法企及的高度new Constuctor()
    }
}
Pool.prototype.get = function(){
	const missile = this.poolAvailable.pop();
	if(!missile) return null;
    this.poolActive.push(missile);
    return missile;
}
Pool.prototype.release = function(missile){
	this.poolActive.splice(this.poolActive.indexOf(missile), 1);
	missile.element.style.display = "none";
    this.poolAvailable.push(missile);
}


function Missile(){
	this.element = document.createElement('div');
	this.element.className = 'missile';
    // 实时属性
    this.velocity = [0, 0];
    this.acceleration = [0, 0];
	this.position = [0, 0]; //offsetLeft是整数，style.left是浮点数，所以不能用offsetLeft作为下一帧的初位置
    this.element.style.display = "none";
}