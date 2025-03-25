struct Ipv4Addr {
    addr: String,
}

struct Ipv6Addr {
    addr: String,
}

enum IpAddr {
    V4(Ipv4Addr),
    V6(Ipv6Addr),
}

enum Message {
    Quit,
    Move { x: i32, y: i32 },
    Write(String),
    ChangeColor(i32, i32, i32),
}

impl Message {
    fn call(&self) {
        println!("hello there");
    }
}

fn main() {
    let four = IpAddr::V4(Ipv4Addr {
        addr: String::from("192.168.1.1"),
    });
    let six = IpAddr::V6(Ipv6Addr {
        addr: String::from("::1"),
    });

    println!(
        "IPv4 address: {}",
        match &four {
            IpAddr::V4(ip) => &ip.addr,
            _ => "Not an IPv4 address",
        }
    );

    println!(
        "IPv6 address: {}",
        match &six {
            IpAddr::V6(ip) => &ip.addr,
            _ => "Not an IPv6 address",
        }
    );
}
