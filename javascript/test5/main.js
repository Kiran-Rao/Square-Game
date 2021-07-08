//target fromes per second
const FPS = 60;
var speed = 1;
var x = 285;
var y = 185;
var xDir = 0;
var yDir = 0;
var timer_c = false;
var timer = 0;

var image = new Image();
image.src = "green.png";
var canvas = null;
var context2D = null;

window.onload = init; // Start the game when the windows loads

function init()
{
	canvas = document.getElementById('canvas');
	context2D = canvas.getContext('2d');
	setInterval(draw, 1000/FPS);
}

function draw()
{
	document.onkeydown = function(event){keystroke(event)}
	movement();
	context2D.clearRect(0, 0, canvas.width, canvas.height);
	context2D.drawImage(image, x, y);
	if (timer_c == true)
		timer++;
	if (timer % 120 == 1)
		speed += 2;
}

function keystroke(event)
{
	if (event.keyCode == 37) { //left arrow
		xDir = -1;
		timer_c = true; 
	}
	if (event.keyCode == 39) { //right arrow
		xDir = 1;
		timer_c = true;
	}
	if (event.keyCode == 38) { //up arrow
		yDir = -1;
		timer_c = true;
	}
	if (event.keyCode == 40) { //down arrow
		yDir = 1;
		timer_c = true;
	}
}

function movement()
{	
	x += speed * xDir;
	y += speed * yDir;
	//Canvas edge
	if (x > canvas.width - 30)
		reset;
	if (x < 0)
		reset;
	if (y > canvas.height - 30)
		reset;
	if (y < 0)
		reset;
}

function reset()
/**
 * Resets the game variables when the game is finished
 * Also computes alerts your score
 */
{
	alert("Your Score is: " + timer);
	x = 285;
	y = 185;
	xDir = 0;
	yDir = 0;
	speed = 3;
	timer = 0;
	timer_c = false;
}