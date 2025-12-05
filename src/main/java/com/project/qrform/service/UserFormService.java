
package com.project.qrform.service;
import org.springframework.stereotype.Service;
import com.project.qrform.entity.UserForm;
import com.project.qrform.repo.UserFormRepo;
@Service
public class UserFormService {
  private final UserFormRepo repo;
  public UserFormService(UserFormRepo repo){ this.repo=repo; }
  public void save(UserForm u){ repo.save(u); }
}
