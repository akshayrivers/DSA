use std::f64::consts::PI;
struct Rectangle {
    width: u32,
    height: u32,
}
impl Rectangle {
    fn area(&self) -> u32 {
        self.width * self.height
    }
    fn can_hold(&self, other: &Rectangle) -> bool {
        self.width > other.width && self.height > other.height
    }
    fn square(size: u32) -> Self {
        Self {
            width: size,
            height: size,
        }
    }
}
struct Circle {
    radius: f64,
}
impl Circle {
    fn area(&self) -> f64 {
        PI * self.radius * self.radius
    }
    fn new(radius: f64) -> Self {
        Self { radius: radius }
    }
}

fn main() {
    let rect1 = Rectangle {
        width: 30,
        height: 50,
    };
    let rect2 = Rectangle {
        width: 20,
        height: 40,
    };
    let circle2 = Circle::new(10.0);
    let sqr = Rectangle::square(10);
    println!("area of the rectangle is {}", rect1.area());
    println!("area of the square is {}", sqr.area());
    println!("area of the circle is {}", circle2.area());
    println!("Can rect1 hold  rect2: {}", rect1.can_hold(&rect2));
}
