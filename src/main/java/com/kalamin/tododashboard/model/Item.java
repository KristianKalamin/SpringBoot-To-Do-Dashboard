package com.kalamin.tododashboard.model;

import com.kalamin.tododashboard.dto.ItemDTO;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private int priority;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_USER_EMAIL"))
    private User user;

    @Contract(pure = true)
    public Item(String title, String description, int priority, User user) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.user = user;
    }

    @Contract(pure = true)
    public Item(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    @Contract(pure = true)
    public Item() {
    }

    public Item(@NotNull ItemDTO itemDTO, User user) {
        this.id = itemDTO.getId();
        this.title = itemDTO.getTitle();
        this.description = itemDTO.getDescription();
        this.priority = itemDTO.getPriority();
        this.endDate = itemDTO.getEndDate();
        this.startDate = itemDTO.getStartDate();
        this.user = user;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public String getUserEmail() {
        return user.getEmail();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Item{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                '}';
    }

    @Contract(value = "null -> false", pure = true)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return priority == item.priority &&
                title.equals(item.title) &&
                description.equals(item.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, priority);
    }
}
