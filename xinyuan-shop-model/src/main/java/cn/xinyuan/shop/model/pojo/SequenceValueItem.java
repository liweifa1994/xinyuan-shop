package cn.xinyuan.shop.model.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "SEQUENCE_VALUE_ITEM")
public class SequenceValueItem implements Serializable {
    /**
     * 表名称
     */
    @Id
    @Column(name = "SEQ_NAME")
    private String seqName;

    /**
     * 序列值
     */
    @Column(name = "SEQ_ID")
    private BigDecimal seqId;

    /**
     * 修改时间
     */
    @Column(name = "LAST_UPDATED_STAMP")
    private Date lastUpdatedStamp;

    /**
     * 创建时间
     */
    @Column(name = "CREATED_STAMP")
    private Date createdStamp;

    private static final long serialVersionUID = 1L;

    /**
     * 获取表名称
     *
     * @return SEQ_NAME - 表名称
     */
    public String getSeqName() {
        return seqName;
    }

    /**
     * 设置表名称
     *
     * @param seqName 表名称
     */
    public void setSeqName(String seqName) {
        this.seqName = seqName;
    }

    /**
     * 获取序列值
     *
     * @return SEQ_ID - 序列值
     */
    public BigDecimal getSeqId() {
        return seqId;
    }

    /**
     * 设置序列值
     *
     * @param seqId 序列值
     */
    public void setSeqId(BigDecimal seqId) {
        this.seqId = seqId;
    }

    /**
     * 获取修改时间
     *
     * @return LAST_UPDATED_STAMP - 修改时间
     */
    public Date getLastUpdatedStamp() {
        return lastUpdatedStamp;
    }

    /**
     * 设置修改时间
     *
     * @param lastUpdatedStamp 修改时间
     */
    public void setLastUpdatedStamp(Date lastUpdatedStamp) {
        this.lastUpdatedStamp = lastUpdatedStamp;
    }

    /**
     * 获取创建时间
     *
     * @return CREATED_STAMP - 创建时间
     */
    public Date getCreatedStamp() {
        return createdStamp;
    }

    /**
     * 设置创建时间
     *
     * @param createdStamp 创建时间
     */
    public void setCreatedStamp(Date createdStamp) {
        this.createdStamp = createdStamp;
    }
}