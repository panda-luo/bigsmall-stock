package club.panda1024.stock.service;

import club.panda1024.stock.mapper.ReviewMapper;
import club.panda1024.stock.model.entity.Review;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:cristopanda@gmail.com"> Panda </a>
 */
@Service
public class ReviewService extends ServiceImpl<ReviewMapper, Review> {
}
