/**
 * Name: Sarena Pham
 * Email: s4pham@ucsd.edu
 * PID: A17555059
 * file contains class User and
 * imports arraylist
 */
import java.util.ArrayList;

/**
 * user is a class with instance vars
 * karma, username, 3 arraylists called
 * posts, upvoted, downvoted
 * methods include up/downvoting, adding a
 * post, updating karma, and finding
 * top post & top comment
 */
public class User {

    private int karma;
    private String username;
    private ArrayList<Post> posts;
    private ArrayList<Post> upvoted;
    private ArrayList<Post> downvoted;

    private static final String TO_STRING_USER_FORMAT = "u/%s Karma: %d";

    /**
     * The constructor for a User.
     * Initialize username to the parameter value and all ArrayLists
     * to empty ArrayLists. Initialize karma to 0.
     * 
     * @param username
     */
    public User(String username) {
        // create array list. references dont point to anything
        this.username = username;
        this.karma = 0;
        this.posts = new ArrayList<Post>();
        this.upvoted = new ArrayList<Post>();
        this.downvoted = new ArrayList<Post>();
    }

    /**
     * Add a Post to end of this User's posts.
     * If post is null, do not add it to posts.
     * Update this User's karma by calling updateKarma()
     * 
     * @param post
     */
    public void addPost(Post post) {
        if (post == null) {
        return;
        }
            this.posts.add(post);
            this.updateKarma(); //
    
    }  

    /**
     * Update user's karma by iterating thru posts
     * to sum up upvoteCountdownvoteCount for each post.
     */
    public void updateKarma() {
        // go thru each post
        for (int row = 0; row < posts.size(); row++) {
            Post pos = posts.get(row);
            this.karma += pos.getUpvoteCount() - pos.getDownvoteCount();
        }
    }

    /**
     * Return the current value of karma. of user
     * 
     * @return karma
     */
    public int getKarma() {
        return karma;
    }

    /**
     * lets user upvote a post. If post param is null/if user upvotes themself
     * stop.
     * special case: if post was downvoted, move it to upvoted arraylist.
     * update up/downvote count for that post
     * update author of the post's karma not the user
     * 
     * @param post
     */
    public void upvote(Post post) {
        if(post == null || post.getAuthor() == this || upvoted.contains(post)){
            return;
        }

        if (downvoted.contains(post)) {

            downvoted.remove(post); // remove param post
            post.updateDownvoteCount(false); // decrase downvote
        }

        post.updateUpvoteCount(true);// increae upvote
        upvoted.add(post);
        post.getAuthor().updateKarma();

    }

    /**
     * the opposite of upvote method
     * 
     * @param post
     */
    public void downvote(Post post) {

        if(post == null || this == post.getAuthor() || downvoted.contains(post)){
            return;
        }
        if (upvoted.contains(post)) {
            upvoted.remove(post); // remove param post
            post.updateUpvoteCount(false); // decrase upvot
        }
        post.updateDownvoteCount(true); // increase downvote
        downvoted.add(post);//
        post.getAuthor().updateKarma(); // update POSTER'S karma not user!!
    }

    /**
     * Return the top original post base on greatest (upvoteCountdownvoteCount).
     * If no such original post exists, return null.
     * If there is a tie, return the lowest index top post
     * 
     * @return Post w top karma
     */
    public Post getTopPost() {
        int origPost = 0;
        if (posts == null)
            return null;
        else {

            Post storePost = null;
            int storeUD = Integer.MIN_VALUE;

            for (int i = 0; i < posts.size(); i++) {

                Post currentPost = posts.get(i);
                if (currentPost.getTitle() != null)// if its a post
                {
                    origPost++;
                    int UDcount = currentPost.getUpvoteCount() - 
                                  currentPost.getDownvoteCount();
                    if (UDcount > storeUD) // update storepost if need, took care of tie
                    {
                        storePost = currentPost;
                        storeUD = currentPost.getUpvoteCount() 
                                 - currentPost.getDownvoteCount();
                    }
                }
            }
            if (origPost == 0)
                return null; // user only had comments
            return storePost;
        }
    }

    /**
     * Return the top comment base on greatest (upvoteCountdownvoteCount) value.
     * If no such comment exists, return null.
     * If there is a tie, return the lowest index first comment Post
     * 
     * @return post top comment
     */
    public Post getTopComment() {
        int comment = 0;
        if (posts == null)
            return null;
        else {
            Post storePost = null;
            int storeUD = Integer.MIN_VALUE;

            for (int i = 0; i < posts.size(); i++) {

                Post currentPost = posts.get(i);
                if (currentPost.getReplyTo() != null)// if its a comment
                {
                    comment++;
                    int UDcount = currentPost.getUpvoteCount() - currentPost.getDownvoteCount();

                    if (UDcount > storeUD) {
                        storePost = currentPost;
                        storeUD = currentPost.getUpvoteCount() - currentPost.getDownvoteCount();
                    }
                }
            }
            if (comment == 0)
                return null; // user only had orig posts
            return storePost;// always stored1st
        }
    }

    /**
     * Return the list of posts (original posts and comments) made by the User.
     * helper method for is a post or comment? do i need to call getPosts() in other
     * method
     * 
     * @return posts arraylist
     */
    public ArrayList<Post> getPosts() {
        return posts;
    }

    /**
     * Return a String representation of this User.
     * Format: u/username Karma: #
     * 
     * @return string
     */
    public String toString() {
    return String.format(TO_STRING_USER_FORMAT, this.username, this.getKarma());
    }
}