package life.majiang.community.service;

import life.majiang.community.dto.PaginationDTO;
import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.exception.CustomizeErrorCode;
import life.majiang.community.exception.CustomizeException;
import life.majiang.community.mapper.QuestionExtMapper;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Question;
import life.majiang.community.model.QuestionExample;
import life.majiang.community.model.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title: QuestionService
 * @Description:
 * @Auther:Leo L Zhang
 * @Version: 1.0
 * @create 2019/10/3 17:50
 */
@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
   private QuestionExtMapper questionExtMapper;

    public PaginationDTO list(Integer page, Integer size) {

        PaginationDTO paginationDTO=new PaginationDTO();
        Integer totalPage;
        Integer totalCount=(int) questionMapper.countByExample(new QuestionExample());

        //calculate total page
        if (totalCount % size==0){
            totalPage = totalCount/size;
        }else {
            totalPage = totalCount/size+1;
        }

        if(page <1){
            page = 1;
        }
        if (page>totalPage){
            page = totalPage;
        }

        paginationDTO.setPagination(totalPage,page);
        //size*（page-1）
        Integer offset = size*(page-1);
        //实现分页需在MyBatis里加上<plugin type="org.mybatis.generator.plugins.RowBoundsPlugin"></plugin>，再重新生成运行mybatis
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(new QuestionExample(),new RowBounds(offset,size));
        List<QuestionDTO> questionDTOList =  new ArrayList<>();

        for (Question question : questions) {
            User user =userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setData(questionDTOList);
        return  paginationDTO;
    }

    public PaginationDTO list(Long userId, Integer page, Integer size) {
        PaginationDTO paginationDTO=new PaginationDTO();
        Integer totalPage;
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria()
                .andCreatorEqualTo(userId);
        Integer totalCount=(int) questionMapper.countByExample(questionExample);
        
        //calculate total page
        if (totalCount % size==0){
            totalPage = totalCount / size;
        }else {
            totalPage = totalCount / size+1;
        }
        if(page < 1){
            page = 1;
        }
        if (page > totalPage){
            page = totalPage;
        }

        paginationDTO.setPagination(totalPage,page);
        //size*(page-1)
        Integer offset = size*(page-1);
        QuestionExample example = new QuestionExample();
        example.createCriteria()
                .andCreatorEqualTo(userId);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(example,new RowBounds(offset,size));
        List<QuestionDTO> questionDTOList =  new ArrayList<>();

        for (Question question : questions) {
            User user =userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setData(questionDTOList);
        return  paginationDTO;

    }

    public void createOrUpdate( Question question) {
        if (question.getId()==null){
            //创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            question.setViewCount(0);
            question.setLikeCount(0);
            question.setCommentCount(0);
            questionMapper.insert(question);
        }else{
            //更新
            Question dbQuestion = questionMapper.selectByPrimaryKey(question.getId());
            if (dbQuestion == null){
                //Throw error
                System.out.println("question  could not be found");
            }
            if(dbQuestion.getCreator().longValue()!=question.getCreator().longValue()){
                //throw error
                System.out.println("creator is not equal");
            }
            //创建更新的问题对象并赋值
            Question updateQuestion = new Question();
            updateQuestion.setGmtModified(System.currentTimeMillis());
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());
            QuestionExample example = new QuestionExample();
            example.createCriteria()
                    .andIdEqualTo(question.getId());
            int updated = questionMapper.updateByExampleSelective(updateQuestion,example);
            if(updated!=1){
                //thow error
                throw  new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }

    }


    public QuestionDTO getById(Long id) {
       Question question= questionMapper.selectByPrimaryKey(id);
       if (question == null){
           throw  new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
       }
       QuestionDTO questionDTO = new QuestionDTO();
       BeanUtils.copyProperties(question,questionDTO);
//       获取User对象
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
       return questionDTO;
    }

    public void incView(Long id) {
        Question question = new Question();
        question.setId(id);
        question.setViewCount(1);
        questionExtMapper.incView(question);
    }
}
