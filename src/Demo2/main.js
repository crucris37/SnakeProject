
var config = {
    type: Phaser.AUTO,
    parent: 'phaser-example',
    width: 1900,
    height: 1050,
    backgroundColor: '#65c1c6',
    scene: {
        preload: preload,
        create: create,
        update: update
    }
};

var game = new Phaser.Game(config);

function preload ()
{
    this.load.image('pic', 'assets/big_boi.jpg');
    this.load.image('pic', 'assets/arrow.jpg');
}

function create ()
{
    this.add.image(0, 0, 'pic').setOrigin(0);

    //var mousePosX = this.game.input.activePointer.worldX;

    //  Set the camera bounds to be the size of the image
    this.cameras.main.setBounds(0, 0, 3000, 2250);

    //  Camera controls


    var cursors = this.input.keyboard.createCursorKeys();

    var controlConfig = {
        camera: this.cameras.main,
        left: cursors.left,
        right: cursors.right,
        up: cursors.up,
        down: cursors.down,
        acceleration: 0.06,
        drag: 0.0005,
        maxSpeed: 1.0
    };

    controls = new Phaser.Cameras.Controls.SmoothedKeyControl(controlConfig);
}

function update (time, delta)
{
    controls.update(delta);
}
