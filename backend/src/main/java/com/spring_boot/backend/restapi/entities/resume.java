package com.spring_boot.backend.restapi.entities;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name="resume")
public class resume {
    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid",strategy="uuid2")
    private String resume_id;

    private String file_name;
    private String file_type;

    @Lob
    private byte[] data;

    private int p_id;


    public resume(){}

   

    public resume( String fileName, String fileType, byte[] data, int p_id) {
       
        this.file_name = fileName;
        this.file_type = fileType;
        this.data = data;
        this.p_id = p_id;
    }



    public String getId() {
        return resume_id;
    }

    public void setId(String id) {
        this.resume_id = id;
    }

    public String getFileName() {
        return file_name;
    }

    public void setFileName(String fileName) {
        this.file_name = fileName;
    }

    public String getFileType() {
        return file_type;
    }

    public void setFileType(String fileType) {
        this.file_type = fileType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }



    public int getP_id() {
        return p_id;
    }



    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    
    

    
}
