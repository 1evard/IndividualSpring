package com.infir.autopartstore.Repos;

import com.infir.autopartstore.Models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepos extends CrudRepository<Post,Long> {
    Post findByTitlepost(String titlepost);

}