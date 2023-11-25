package com.post.apiBlamovipost.repositories;

import com.post.apiBlamovipost.models.PostModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostRepository extends JpaRepository<PostModel, UUID> {
}
