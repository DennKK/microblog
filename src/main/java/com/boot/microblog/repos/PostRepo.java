package com.boot.microblog.repos;

import com.boot.microblog.domain.PostEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepo extends CrudRepository<PostEntity, Long> {
    List<PostEntity> findByTag(String tag);
}