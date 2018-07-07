package www.mp5a5.com.ueye.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * @author ：king9999 on 2018/6/25 10：40
 * @describe
 * @email：wwb199055@enn.cn
 */
@Entity
public class VideoEntityCache {

  @Id(autoincrement = true)
  private Long id;

  private int palyerId;

  private String url;

  private String videoBean;

  @Generated(hash = 1412485992)
  public VideoEntityCache(Long id, int palyerId, String url, String videoBean) {
      this.id = id;
      this.palyerId = palyerId;
      this.url = url;
      this.videoBean = videoBean;
  }

  @Generated(hash = 1388751497)
  public VideoEntityCache() {
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getPalyerId() {
    return this.palyerId;
  }

  public void setPalyerId(int palyerId) {
    this.palyerId = palyerId;
  }

  public String getUrl() {
    return this.url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getVideoBean() {
    return this.videoBean;
  }

  public void setVideoBean(String videoBean) {
    this.videoBean = videoBean;
  }


  @Override
  public String toString() {
    return "VideoEntityCache{" +
        "id=" + id +
        ", palyerId=" + palyerId +
        ", url='" + url + '\'' +
        ", videoBean='" + videoBean + '\'' +
        '}';
  }
}
