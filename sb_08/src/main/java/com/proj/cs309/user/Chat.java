package com.proj.cs309.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Entity
@Table(name = "messages")
public class Chat {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotNull
    @Size(max = 100)
    @Column
    private String userName;
	
	@NotNull
    @Lob
    private String content;
	
	@NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "sent")
    private Date sent = new Date();
	
	
	public Chat() {};
	
	public Chat(String userName, String content) {
		this.userName = userName;
		this.content = content;
	}
	
	public String getUserName() {
        return this.userName;
    }
	
	public String getContent() {
        return this.content;
    }
}
