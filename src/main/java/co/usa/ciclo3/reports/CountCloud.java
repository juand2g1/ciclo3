package co.usa.ciclo3.reports;

import co.usa.ciclo3.ciclo3.model.Cloud;

public class CountCloud {
private Long total;
private Cloud cloud;

    public CountCloud() {
    }

    public CountCloud(Long total, Cloud cloud) {
        this.total = total;
        this.cloud = cloud;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Cloud getCloud() {
        return cloud;
    }

    public void setCloud(Cloud cloud) {
        this.cloud = cloud;
    }
}
