/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.badway.db.test;

import net.badway.db.annotation.Id;
import net.badway.db.annotation.ManyToOne;
import net.badway.db.annotation.SqlField;

/**
 *
 * @author jonathan
 */
public class Message {
    @Id(autoIncrement = true)
    @SqlField(sqlType = "INT")
    private Integer id;
    @ManyToOne
    private User destinataire;
    @ManyToOne
    private User emmeteur;
    @SqlField( sqlType = "TEXT")
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(User destinataire) {
        this.destinataire = destinataire;
    }

    public User getEmmeteur() {
        return emmeteur;
    }

    public void setEmmeteur(User emmeteur) {
        this.emmeteur = emmeteur;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    
    
}
