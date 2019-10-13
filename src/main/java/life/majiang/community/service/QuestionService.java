package life.majiang.community.service;

import life.majiang.community.dto.PaginationDTO;
import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Question;
import life.majiang.community.model.QuestionExample;
import life.majiang.community.model.User;
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

    public PaginationDTO list(Integer page, Integer size) {

        PaginationDTO paginationDTO=new PaginationDTO();
        Integer totalPage;
        Integer totalCount=questionMapper.count();

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
        List<Question> questions = questionMapper.list(offset,size);
        List<QuestionDTO> questionDTOList =  new ArrayList<>();

        for (Question question : questions) {
//           User user =userMapper.findByID(question.getCreator());
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
        Integer totalCount=questionMapper.countByUserID(userId);
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
        List<Question> questions = questionMapper.listByUserId(userId,offset,size);
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
                System.out.println("Update fail");
            }
        }

    }


    public QuestionDTO getById(Long id) {
       Question question= questionMapper.getById(id);
       QuestionDTO questionDTO = new QuestionDTO();
       BeanUtils.copyProperties(question,questionDTO);
//       获取User对象
        User user = userMapper.findByID(question.getCreator());
        questionDTO.setUser(user);
       return questionDTO;
    }
}
