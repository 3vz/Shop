package com.keratin.dao.product;

import org.springframework.stereotype.Repository;

import com.keratin.dao.DaoSupport;
import com.keratin.model.product.UploadFile;
@Repository("uploadFileDao")
public class UploadFileDaoImpl extends DaoSupport<UploadFile> implements UploadFileDao {

}
