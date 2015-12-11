package br.com.dataeasy.visualizador.model;

public class GroupDocsInfo {
    private String fileId;
    private String userName;
    private String userGuid;
    private String errorMessage;

    public GroupDocsInfo() {
        super();
    }

    public GroupDocsInfo(String fileId, String userName, String userGuid) {
        super();
        this.fileId = fileId;
        this.userName = userName;
        this.userGuid = userGuid;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String filePath) {
        this.fileId = filePath;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserGuid() {
        return userGuid;
    }

    public void setUserGuid(String userGuid) {
        this.userGuid = userGuid;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
