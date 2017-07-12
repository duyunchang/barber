package com.geekcattle.manager.console;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geekcattle.domain.entity.console.Log;

/**
 * author 
 */
public interface LogMapper  extends JpaRepository<Log ,String> {
}
