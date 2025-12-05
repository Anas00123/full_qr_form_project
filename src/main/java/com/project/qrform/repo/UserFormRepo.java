
package com.project.qrform.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.qrform.entity.UserForm;

public interface UserFormRepo extends JpaRepository<UserForm, Long> {
}
