package com.boot.microblog.repos;

import com.boot.microblog.domain.PostEntity;
import org.springframework.data.repository.CrudRepository;

public interface PostRepo extends CrudRepository<PostEntity, Long> {
}