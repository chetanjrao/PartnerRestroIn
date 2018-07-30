package in.restroin.partnerrestroin.models;

public class ReviewsModel {
    public String reviewer_name, review;
    public int reviewer_image;
    public float review_rating;

    public ReviewsModel(String reviewer_name, String review, int reviewer_image, float review_rating) {
        this.reviewer_name = reviewer_name;
        this.review = review;
        this.reviewer_image = reviewer_image;
        this.review_rating = review_rating;
    }

    public String getReviewer_name() {
        return reviewer_name;
    }

    public void setReviewer_name(String reviewer_name) {
        this.reviewer_name = reviewer_name;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getReviewer_image() {
        return reviewer_image;
    }

    public void setReviewer_image(int reviewer_image) {
        this.reviewer_image = reviewer_image;
    }

    public float getReview_rating() {
        return review_rating;
    }

    public void setReview_rating(float review_rating) {
        this.review_rating = review_rating;
    }
}
