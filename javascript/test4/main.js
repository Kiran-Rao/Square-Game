//target fromes per second
const FPS = 60;
var speed = 2;
var x = 5;
var y = 5;
var xDir = 1;
var yDir = 1;
var timer_c = false;
var timer = 0;

var image = new Image();
image.src = "green.png";
var canvas = null;
var context2D = null;

window.onload = init;

function init()
{
	canvas = document.getElementById('canvas');
	context2D = canvas.getContext('2d');
//	refreshScores();
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
	if (timer % 60 == 1)
		speed += 1;
}

function keystroke(event)
{
	if (event.keyCode == 37) { //left
		xDir = -1;
		timer_c = true;
	}
	if (event.keyCode == 39) { //right
		xDir = 1;
		timer_c = true;
	}
	if (event.keyCode == 38) { //up
		yDir = -1;
		timer_c = true;
	}
	if (event.keyCode == 40) { //down
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
		reset();
	if (x < 0)
		reset();
	if (y > canvas.height - 30)
		reset();
	if (y < 0)
		reset();
}

function reset()
{
	if (timer != 0)
	{
		var base = "http://www.pahgawks.com/tony/kiran.php";
		var namestring = encodeURI(prompt("Your Score is: " + timer + ", Your name:"));
		document.createElement("img")
		.src=base + "?name="+namestring+"&score="+timer.toString();
	}
	x = 5;
	y = 5;
	xDir = 1;
	yDir = 1;
	speed = 2;
	timer = 0;
	timer_c = false;
}