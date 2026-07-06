import * as THREE from 'three';
import {GLTFLoader} from 'three/addons/loaders/GLTFLoader.js';

const canvas = document.querySelector('canvas');
const renderer = new THREE.WebGLRenderer({antialias: true, canvas, alpha: true});
renderer.setSize(window.innerWidth, window.innerHeight);

const scene = new THREE.Scene();
scene.position.set(0, -2, 0);
scene.add(new THREE.AmbientLight(0xffffff, 1));

const gltfLoader = new GLTFLoader();
gltfLoader.load("model/cake01/a42a121b6a7f48e68862c1dfaa23e26e.gltf", (gltf) => {
    const root = gltf.scene;
	// root.scale.set(5,5,5);
    scene.add(root);
	// renderer.render(scene, camera);
});

const d = 5;
const camera = new THREE.PerspectiveCamera(75, window.innerWidth / window.innerHeight, 0.1, 500);
camera.position.set(0, d*Math.sin(Math.PI/4), d*Math.sin(Math.PI/4));
camera.lookAt(0, 0, 0);
camera.up.set(0, 1, 0);

animate();
function animate() {
	renderer.render(scene, camera);
	requestAnimationFrame(animate);
}

let OX = window.innerWidth / 2, OY = window.innerHeight / 2;
window.addEventListener('resize', () => {
	console.log(canvas.clientWidth, canvas.clientHeight);
	OX = window.innerWidth / 2, OY = window.innerHeight / 2;
	camera.aspect = window.innerWidth / window.innerHeight;
	camera.updateProjectionMatrix();
	renderer.setSize(window.innerWidth, window.innerHeight);
});

let target = [0, 0, 0];    // 相机围绕的中心点
let theta = 0;             // 水平角
let phi = Math.PI / 4;     // 垂直角
window.addEventListener("mousemove", e => {
	theta = Math.PI/window.innerWidth * (OX-e.clientX);
	phi = Math.PI/window.innerHeight * (e.clientY-OY) + Math.PI/4;

	const x = target[0] + d * Math.sin(theta) * Math.cos(phi);
	const y = target[1] + d * Math.sin(phi);
	const z = target[2] + d * Math.cos(theta) * Math.cos(phi);

	camera.position.set(x, y, z);
	camera.lookAt(...target);
});