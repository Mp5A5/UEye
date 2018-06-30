package www.mp5a5.com.ueye.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author ：king9999 on 2018/6/25 10：40
 * @describe
 * @email：wwb199055@enn.cn
 */
@Entity
public class VideoEntityCache {

  @Id
  private int id;

  //private int count;

  private String url;

  private String videoBean;

  @Generated(hash = 1959170266)
public VideoEntityCache(int id, String url, String videoBean) {
    this.id = id;
    this.url = url;
    this.videoBean = videoBean;
}

@Generated(hash = 1388751497)
public VideoEntityCache() {
}

public int getId() {
      return this.id;
  }

  public void setId(int id) {
      this.id = id;
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
}
