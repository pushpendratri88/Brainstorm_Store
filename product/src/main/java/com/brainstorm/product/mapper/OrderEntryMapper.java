//package com.brainstorm.product.mapper;
//
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//public class OrderEntryMapper {
//    public static List<OrderEntry> mapToOrderEntry(List<OrderEntryDTO> orderEntryListDTO,   ProductRepository productRepository,  OrderEntryRepository orderEntryRepository){
//        List<OrderEntry> orderEntryList = new ArrayList<>();
//        orderEntryListDTO.forEach(orderEntryDTO -> {
//            Product product = getProductFormProductId(orderEntryDTO.getProductId() , productRepository);
//            OrderEntry orderEntry = new OrderEntry();
//            orderEntry.setCreatedAt(LocalDateTime.now());
//            orderEntry.setQuantity(orderEntryDTO.getQuantity());
//            orderEntry.setPrice(product.getPrice() * orderEntryDTO.getQuantity());
//            orderEntry.setProduct(product);
//            OrderEntry orderEntryTr = orderEntryRepository.saveAndFlush(orderEntry);
//            orderEntryList.add(orderEntryTr);
//        });
//        return orderEntryList;
//    }
//
//    private static Product getProductFormProductId(String productId, ProductRepository productRepository) {
//        return productRepository.findById(productId).orElseThrow();
//    }
//
//
//    public static OrderEntryDTO mapToOrderEntryDTO(OrderEntry orderEntry){
//        OrderEntryDTO orderEntryDTO = new OrderEntryDTO();
//        orderEntryDTO.setId(orderEntry.getId());
//        orderEntryDTO.setQuantity(orderEntry.getQuantity());
//        orderEntryDTO.setPrice(orderEntry.getProduct().getPrice() * orderEntry.getQuantity());
//        orderEntryDTO.setProductDTO(ProductMapper.mapToProductDTO(orderEntry.getProduct()));
//        return orderEntryDTO;
//    }
//}
