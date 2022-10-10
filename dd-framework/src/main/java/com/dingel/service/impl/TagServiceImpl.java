package com.dingel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingel.domain.entity.Tag;
import com.dingel.mapper.TagMapper;
import com.dingel.service.TagService;
import org.springframework.stereotype.Service;

/**
 * 标签(Tag)表服务实现类
 *
 * @author makejava
 * @since 2022-10-10 10:49:59
 */
@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

}

