// Key things learned and solidified:
// 1. Box() - a smart pointer
// 2. Option<T> - just an enum with values None and Some
// 3. match hmm very good when you understand it
// 4.  while let - a very sleek and good of writing the loop like this :
// loop {
//     match temp.next {
//         Some(ref mut next_node) => {
//             temp = next_node;
//         }
//         None => break,
//     }
// }
// 5.take() takes the ownwership and and_then() does something if the previous . is true means if it Some
//example:
//let mut option = Some(10);
//let value = option.take();  // `option` is now `None`, and `value` is `Some(10)`

//and_then() is used for chaining computations on the value inside an Option (or Result).
// It allows you to apply a function to the value inside an Option (if it is Some), but only if it is not None.
//If the Option is None, it skips the function and returns None.
struct Node {
    data: i32,
    next: Option<Box<Node>>,
}
impl Node {
    fn new(data: i32) -> Self {
        Node { data, next: None }
    }
}

struct LinkedList {
    head: Option<Box<Node>>,
}
impl LinkedList {
    fn new() -> Self {
        LinkedList { head: None }
    }
    fn insert(&mut self, val: i32) {
        let new_node = Box::new(Node::new(val));
        match self.head {
            None => {
                self.head = Some(new_node);
            }
            Some(ref mut current_node) => {
                let mut temp = current_node;
                // phew very weird way to loop in Rust but okay i got it
                // loop {
                //     match temp.next {
                //         Some(ref mut next_node) => {
                //             temp = next_node;
                //         }
                //         None => break,
                //     }
                // }
                while let Some(ref mut next_node) = temp.next {
                    temp = next_node;
                }
                temp.next = Some(new_node);
            }
        }
    }

    fn insert_at_beg(&mut self, val: i32) {
        let mut new_node = Box::new(Node::new(val));
        new_node.next = self.head.take();
        self.head = Some(new_node);
    }
    fn delete_at_pos(&mut self, pos: i32) {
        if pos <= 0 || self.head.is_none() {
            return;
        }
        let mut temp = self.head.take();
        if pos == 1 {
            self.head = temp.take().and_then(|node| node.next);
            return;
        }
        let mut current_pos = 1;
        while let Some(mut node) = temp {
            if current_pos == pos - 1 {
                // Take the node to be deleted
                if let Some(mut node_to_delete) = node.next.take() {
                    // Skip the node
                    node.next = node_to_delete.next.take();
                }
                self.head = Some(node);
                return;
            }

            current_pos += 1;
            temp = node.next.take();
        }
    }
    fn print_list(&self) {
        let mut current = &self.head;
        while let Some(ref node) = current {
            print!("{} -> ", node.data);
            current = &node.next;
        }
        println!("None");
    }
}
fn main() {
    let mut ll = LinkedList::new();
    ll.insert(9);
    ll.insert(10);
    ll.insert(11);
    ll.insert(12);
    ll.insert(13);
    ll.insert(14);
    ll.insert(15);
    ll.insert(16);
    ll.insert_at_beg(8);
    ll.delete_at_pos(2);
    ll.print_list()
}
