package com.cloud.product.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述
 *
 * @author: renpenghui
 * @date: 2019-07-25 09:43
 **/
@RestController
@RequestMapping("book")
public class BookController {


//    @Autowired
//    private BookRepository bookRepository;
//
//    @Autowired
//    private ElasticsearchTemplate elasticsearchTemplate;
//
//    @GetMapping("insert")
//    public Book insert(){
//        Book book = new Book();
//        book.setBookId(2L);
//        book.setAuthor("余华");
//        book.setTitle("活着");
//        book.setKeyword("余华，活着，徐福贵");
//        book.setContent("《活着》是作家余华的代表作之一，讲述了在大时代背景下，随着内战、三反五反，大跃进，文化大革命等社会变革，" +
//                "徐福贵的人生和家庭不断经受着苦难，到了最后所有亲人都先后离他而去，仅剩下年老的他和一头老牛相依为命。");
//        book.setWords(6800000L);
//        book.setPublishDate(new Date());
//        book.setCreateAt(new Date());
//        bookRepository.save(book);
//        return book;
//    }
//
//    @GetMapping("get/{bookId}")
//    public Book getBook(@PathVariable("bookId") Long bookId){
//         Optional<Book> optional = bookRepository.findById(bookId);
//        return optional.get();
//    }
//
//    @GetMapping("getAll")
//    public Iterable<Book> getAll(){
//        Iterable<Book> all = bookRepository.findAll();
//        return all;
//    }
//
//    @GetMapping("updateByQuery")
//    public Iterable<Book> updateByQuery(){
//        Book book = new Book();
//        book.setBookId(2L);
//        book.setAuthor("余华");
//        book.setTitle("活着2222");
//        book.setKeyword("余华，活着，徐福贵");
//        book.setContent("《活着》是作家余华的代表作之一，讲述了在大时代背景下，随着内战、三反五反，大跃进，文化大革命等社会变革，" +
//                "徐福贵的人生和家庭不断经受着苦难，到了最后所有亲人都先后离他而去，仅剩下年老的他和一头老牛相依为命。");
//        book.setWords(6800000L);
//        book.setPublishDate(new Date());
//        book.setCreateAt(new Date());
//        return null;
//    }
//
//
//    @GetMapping("getList")
//    public Page<Book> getList(){
//        Sort sort = new Sort(Sort.Direction.DESC, "bookId");//以id值为准 降序排列，ASC为升序
//        Pageable pageable = new PageRequest(0, 10, sort);//查看第0页，以每页10条划分
//        SearchQuery searchQuery = new NativeSearchQueryBuilder()
//                .withQuery(QueryBuilders.matchAllQuery()) // 自定义查询（这是不同的地方）
//                .withPageable(pageable) // 自定义分页
//                .build();
//        Page<Book> page = elasticsearchTemplate.queryForPage(searchQuery,Book.class);
//        System.out.println("页数" + page.getTotalPages());
//        System.out.println("总条数" + page.getTotalElements());
//        System.out.println("每页大小" + page.getSize());
//        System.out.println("当前第几页" + page.getNumber());
//        System.out.println("当前页的数量"+page.getNumberOfElements());
//        System.out.println("List<Book>:"+page.getContent());
//        return page;
//    }
//
//
//

}
