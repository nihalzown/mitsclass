let mouseX = 0;
let mouseY = 0;

document.addEventListener('mousemove', function(e) {
    mouseX = e.pageX;
    mouseY = e.pageY;
});

function createTrail(x, y) {
    const trail = document.createElement('div');
    trail.className = 'trail';
    trail.style.left = x + 'px';
    trail.style.top = y + 'px';
    document.body.appendChild(trail);

    setTimeout(() => {
        trail.remove();
    }, 300); // Match with the CSS animation duration
}

function animate() {
    const follower = document.getElementById('follower');
    follower.style.left = mouseX + 'px';
    follower.style.top = mouseY + 'px';

    createTrail(mouseX, mouseY);

    requestAnimationFrame(animate);
}

animate();