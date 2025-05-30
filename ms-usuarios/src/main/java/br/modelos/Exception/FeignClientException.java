package br.modelos.Exception;

public class FeignClientException extends RuntimeException {

    private final int status;
    private final String detailMessage;

    public FeignClientException(int status, String detailMessage) {
        super(detailMessage);
        this.status = status;
        this.detailMessage = detailMessage;
    }

    public int getStatus() {
        return status;
    }

    public String getDetailMessage() {
        return detailMessage;
    }
}
