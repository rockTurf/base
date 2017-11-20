package com.srj.web.sys.mapper;

import java.util.List;

import com.github.abel533.mapper.Mapper;
import com.srj.web.sys.model.SysFile;

public interface SysFileMapper extends Mapper<SysFile>{

	Integer saveFileList(List<SysFile> listrecord);

}
