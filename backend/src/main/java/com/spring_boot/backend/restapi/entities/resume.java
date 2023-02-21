package com.spring_boot.backend.restapi.entities;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="resume")
public class resume {
    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid",strategy="uuid2")
    private String resumeid;

    private String file_name;
    private String file_type;

    @Lob
    private byte[] data;

    @OneToOne
    @JoinColumn(name="pId")
    private candidateProfile cProfile;

 
    public resume(){}

   
    public resume(String file_name, String file_type, byte[] data, candidateProfile cProfile) {
        this.file_name = file_name;
        this.file_type = file_type;
        this.data = data;
        this.cProfile = cProfile;
    }

    public String getId() {
        return resumeid;
    }

    public void setId(String id) {
        this.resumeid = id;
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


    public candidateProfile getcProfile() {
        return cProfile;
    }


    public void setcProfile(candidateProfile cProfile) {
        this.cProfile = cProfile;
    }

    
}
