package com.bridgelabz.bookstore.order.service;
;
import com.bridgelabz.bookstore.bookservice.config.Emailsender;
import com.bridgelabz.bookstore.bookservice.entity.BookEntity;
import com.bridgelabz.bookstore.bookservice.repository.BookRepository;
import com.bridgelabz.bookstore.cart.repository.CartRepository;
import com.bridgelabz.bookstore.order.dto.OrderDTO;
import com.bridgelabz.bookstore.order.entity.OrderData;
import com.bridgelabz.bookstore.order.repository.OrderRepository;
import com.bridgelabz.bookstore.user.entity.UserEntity;
import com.bridgelabz.bookstore.user.repository.IBookStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private IBookStoreRepository userRegistrationRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private Emailsender mailService;

    @Override
    public OrderData placeOrder(int userId, OrderDTO orderDto) {

        UserEntity user = userRegistrationRepository.findById(userId).orElse(null);

        BookEntity book = bookRepository.findById(orderDto.getBookId()).orElse(null);

        if (user != null) {

            int orderPrice = book.getBookPrice() * orderDto.getQuantity();
            book.setBookQuantity(book.getBookQuantity()-orderDto.getQuantity());

            OrderData order = new OrderData(user,book,orderPrice,orderDto);
            orderRepository.save(order);
            cartRepository.deleteAll();
            mailService.sendEmail(user.getUserEmailId(),"Order Placed",
                    "Book Name :"+order.getBook().getBookName()+
                            "\n" +"Book Description :"+order.getBook().getBookDescription()+
                            "\n" +"Book Price :"+order.getBook().getId()+
                            "\n" +"Order Quantity :"+orderDto.getQuantity() +
                            "\n" +"Order Price :"+orderPrice);
            return order;
        }
        return null;
    }

    @Override
    public String cancelOrder(int orderId, int userId) {

        UserEntity user = userRegistrationRepository.findById(userId).orElse(null);
        if (user != null) {

            OrderData order = orderRepository.findById(orderId).orElse(null);

            if (order != null) {
                order.setCancel(true);
                BookEntity book = bookRepository.findById(order.getId()).orElse(null);
                book.setBookQuantity(book.getBookQuantity()+ order.getQuantity());
                mailService.sendEmail(user.getUserEmailId(),"Order Cancelled","Order Id " + orderId+"\n"+order);
                orderRepository.save(order);
                return "Order Cancelled";
            }
        }
        return "User Not Found !!";
    }

    @Override
    public List<OrderData> userOrders(int userId) {
        UserEntity user = userRegistrationRepository.findById(userId).orElse(null);

        if (user != null) {
            List<OrderData> order = orderRepository.findAllByUserId(userId);
            return order;
        }
        return null;
    }


}