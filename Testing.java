
public class Testing {
    public static void main(String[] args) {
        User u1 = new User("CSE11Student");
        User u2 = new User("up/downvoter guy");
        Post p1 = new Post("Title", "Content", u1);
        Post p2 = new Post("random", "blank", u1);
        Post c1 = new Post("Comment", p1, u1);
        System.out.println(u1);
        System.out.println(u2);
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(c1);
        u1.addPost(null);
        u1.addPost(p2);
        u1.addPost(c1);
        System.out.println("test u2");
        u1.upvote(p1);
        u1.downvote(p2);
        u2.upvote(p1);
        u2.downvote(p1);
        u2.downvote(p2);
        u2.upvote(p2);
        System.out.println(p2.getThread());
        System.out.println(u1.getTopPost());
        System.out.println(u1.getTopComment());
    }
}