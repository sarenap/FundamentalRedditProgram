
import java.util.ArrayList;

/**
 * class post imports arraylist, contains methods to
 * get title, author of post or comment, get posts/comments,
 * get thread of posts, get and update up/downvote count
 */
public class SinglePost {
    // instance vars
    private String title;
    private String content;
    private Post replyTo;
    private User author;
    private int upvoteCount;
    private int downvoteCount;

    private static final String TO_STRING_POST_FORMAT = "[%d|%d]\t%s\n\t%s";
    private static final String TO_STRING_COMMENT_FORMAT = "[%d|%d]\t%s";

    /**
     * The constructor for initializing an original post
     * @param title content author
     */
    public Post(String title, String content, User author) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.upvoteCount = 1;
        this.downvoteCount = 0;
    }

    /**
     * The constructor for initializing a comment.
     * @param content replytoautor
     */
    public Post(String content, Post replyTo, User author) {
        this.content = content;
        this.replyTo = replyTo;
        this.author = author;
        this.upvoteCount = 1;
        this.downvoteCount = 0;
    }

    /**
     * get title
     * @return string
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * getreplyto
     * @return post comment
     */
    public Post getReplyTo() {
        return this.replyTo;
    }

    /**
     * @return author of this Post.
     */
    public User getAuthor() {
        return this.author;
    }

    /**
     * getupvotecount
     * @return the number of upvotes of this Post.
     */
    public int getUpvoteCount() {
        return this.upvoteCount;
    }

    /**
     * getdownvotescount
     * @return Return post's downvote count
     */
    public int getDownvoteCount() {
        return this.downvoteCount;
    }

    /**
     * increase/decrease upvote count
     * 
     * @param isIncrement
     */
    public void updateUpvoteCount(boolean isIncrement) {
        if (isIncrement) {
            upvoteCount++;
        } else {
            upvoteCount--;
        }
    }

    /**
     * increase/decrease downvote count
     * 
     * @param isIncrement
     */
    public void updateDownvoteCount(boolean isIncrement) {
        if (isIncrement) {
            downvoteCount++;
        } else {
            downvoteCount--;
        }
    }

    /**
     * Return a list of posts in the current thread, start with the original post,
     * end with this post. Used iteration
     * 
     * @return arraylist of posts
     */
    public ArrayList<Post> getThread() {

        ArrayList<Post> thread = new ArrayList<Post>();

        Post current = this;
        thread.add(this); // add this post

        while (current.getReplyTo() != null) {
            current = current.getReplyTo(); // update
            thread.add(0, current);
            // add post to beginning since we go bottom to up
        }

        return thread;
    }

    /**
     * If this Post is an original post, the toString() should have the
     * following format (\t is a tab):
     * 
     * @return String representation of this Post.
     */
    public String toString() {
        // if its a title
        if (this.getReplyTo() == null) {
            return String.format(TO_STRING_POST_FORMAT, this.getUpvoteCount(), this.getDownvoteCount(), this.getTitle(),
                    this.content);
        } else {
            return String.format(TO_STRING_COMMENT_FORMAT, this.getUpvoteCount(), this.getDownvoteCount(),
                    this.content);
        }
    }
}