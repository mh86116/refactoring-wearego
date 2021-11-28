package com.greedy.TravelWithGuid.guide.service.impl;

import com.greedy.TravelWithGuid.guide.model.dto.GoodsDTO;
import com.greedy.TravelWithGuid.guide.model.entity.Attachment;
import com.greedy.TravelWithGuid.guide.model.entity.Goods;
import com.greedy.TravelWithGuid.guide.model.entity.GoodsOption;
import com.greedy.TravelWithGuid.guide.model.entity.Guide;
import com.greedy.TravelWithGuid.guide.model.enums.PhotoCategory;
import com.greedy.TravelWithGuid.guide.repository.AttachmentRepository;
import com.greedy.TravelWithGuid.guide.repository.GoodsOptionRepository;
import com.greedy.TravelWithGuid.guide.repository.GoodsRepository;
import com.greedy.TravelWithGuid.guide.service.GoodsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class GoodsServiceImpl implements GoodsService {
    private final GoodsRepository goodsRepository;
    private final GoodsOptionRepository optionRepository;
    private final AttachmentRepository attachmentRepository;

    @Override
    public boolean goodsFileUpload(List<MultipartFile> multipartFile, GoodsDTO dto, List<String> optionName, List<String> optionPrice, Guide guide) {
        Goods goods = Goods.builder()
                .guide(guide)
                .title(dto.getTitle())
                .price(Integer.valueOf(dto.getPrice()))
                .place(dto.getPlace())
                .startDt(LocalDate.parse(dto.getStartDt()))
                .endDt(LocalDate.parse(dto.getEndDt()))
                .person(Integer.valueOf(dto.getPerson()))
                .body(dto.getBody())
                .isEnable(false)
                .build();
        goodsRepository.save(goods);
        if (!goods.isNew()) {
            /*******************************************************************
             * option save
             ******************************************************************/
            String name = "";
            String price = "";
            for (int i = 0; i < optionName.size(); i++) {
                name = optionName.get(i);
                System.out.println("name = " + name);
                for (int j = 0; j < optionPrice.size(); j++) {
                    price = optionPrice.get(i);
                    System.out.println("price = " + price);
                }
                GoodsOption option = GoodsOption.builder()
                        .goods(goods)
                        .optionName(name)
                        .optionPrice(price)
                        .isEnable(false)
                        .build();
                optionRepository.save(option);
            }
            /*******************************************************************
             * image save
             ******************************************************************/
            for (MultipartFile file : multipartFile) {
                System.out.println("file.getOriginalFilename() = " + file.getOriginalFilename());
                int dot = Objects.requireNonNull(file.getOriginalFilename()).lastIndexOf(".");
                String ext = file.getOriginalFilename().substring(dot);
                String savedName = UUID.randomUUID().toString().replace("-", "") + ext;

                File filePath = new File(System.getProperty("user.dir") + "/src/main/resources/static/img/upload");
                if (!filePath.exists()) {
                    System.out.println("폴더 생성 = " + filePath.mkdirs());
                }
                File filePath2 = new File(filePath + "/" + savedName);
                try {
                    FileOutputStream newFilePath = new FileOutputStream(filePath2, true);
                    newFilePath.write(file.getBytes());

                    Attachment attachment = Attachment.builder()
                            .category(PhotoCategory.valueOf(PhotoCategory.GOODS.getVaule()))
                            .originalName(file.getOriginalFilename())
                            .savedName(savedName)
                            .savePath(filePath.getPath())
                            .isEnable(String.valueOf(false))
                            .build();
                    attachmentRepository.save(attachment);
                    System.out.println("attachment = " + attachment);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return true;
        } else {
            return false;
        }
    }
}
