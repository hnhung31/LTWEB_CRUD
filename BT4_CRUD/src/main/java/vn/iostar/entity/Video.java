package vn.iostar.entity;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table(name = "videos")
@NamedQuery(name = "Video.findAll", query = "SELECT v FROM Video v")
public class Video implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "videoId")
	private String videoId;
	@Column(name = "active")
	private boolean active;
	@Column(name = "description", columnDefinition = "NVARCHAR(500) NULL")
	private String description;
	@Column(name = "poster", columnDefinition = "NVARCHAR(500) NULL")
	private String poster;
	@Column(name = "title", columnDefinition = "NVARCHAR(500) NULL")
	private String title;
	@Column(name = "views")
	private int views;

	// bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name = "categoryId") //truong database
	private Category categories;

	public Video() {

	}

	public String getVideoId() {

		return this.videoId;

	}

	public void setVideoId(String videoId) {

		this.videoId = videoId;

	}

	public boolean getActive() {

		return this.active;

	}

	public void setActive(boolean active) {

		this.active = active;

	}

	public String getDescription() {

		return description;

	}

	public void setDescription(String description) {

		this.description = description;

	}

	public String getPoster() {

		return poster;

	}

	public void setPoster(String poster) {

		this.poster = poster;

	}

	public String getTitle() {

		return title;

	}

	public void setTitle(String title) {

		this.title = title;

	}

	public int getViews() {

		return this.views;

	}

	public void setViews(int views) {

		this.views = views;

	}

	public Category getCategory() {

		return this.categories;

	}

	public void setCategory(Category category) {

		this.categories = category;

	}
}
