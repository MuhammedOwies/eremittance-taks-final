package com.evision.finance.eremittance.apis.service;

import com.evision.finance.eremittance.apis.entity.PartnerEO;
import com.evision.finance.eremittance.authenticator.entity.PageVO;
import com.evision.finance.eremittance.authenticator.entity.enums.ErrorRef;
import com.evision.finance.eremittance.authenticator.entity.enums.ResponseRef;
import com.evision.finance.eremittance.apis.view.PartnerVO;
import com.evision.finance.eremittance.authenticator.service.BasicAbstractService;
import com.evision.finance.eremittance.authenticator.util.MessageTranslator;
import com.evision.finance.eremittance.authenticator.util.RemittanceAppException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class PartnerServiceImpl extends BasicAbstractService<PartnerEO> {

    public PartnerServiceImpl(ModelMapper modelMapper, JpaRepository<PartnerEO, Long> jpaRepository) {
        super(modelMapper, jpaRepository);
    }

//    public Long savePartner(PartnerExtendedVO vo) {
//        if (!super.exists(modelMapper.map(vo, PartnerEO.class), new String[]{"id", "logo", "phoneNumber", "faxNumber", "addressLine", "country"})) {
//            return super.save(modelMapper.map(vo, PartnerEO.class)).getId();
//        } else
//            throw new RemittanceAppException(ErrorRef.PARTNER_ALREADY_EXIST, ResponseRef.BAD_REQUEST, MessageTranslator
//                    .toLocale("Partner.EntryAlreadyExists", new Object[]{vo.getName()}));
//    }

    public List<PartnerVO> saveAllPartners(List<PartnerVO> vos) {
        try {
            return super.saveAll(vos.stream().map(vo -> modelMapper.map(vo, PartnerEO.class)).collect(Collectors.toList())).stream().map(eo -> modelMapper.map(eo, PartnerVO.class)).collect(Collectors.toList());
        } catch (RuntimeException exp) {
            throw new RemittanceAppException(ErrorRef.GENERAL_ERROR, ResponseRef.SERVER_ERROR, exp.getMessage(), exp);
        }
    }

    public PartnerVO getTradingPartnerById(Long id) {
        try {
            return modelMapper.map(Optional.ofNullable(super.findById(id)).orElseThrow(() -> new RemittanceAppException(ErrorRef.PARTNER_NOT_FOUND, ResponseRef.NOT_FOUND, MessageTranslator
                    .toLocale("Partner.EntryNotFound", new Object[]{id}))), PartnerVO.class);
        } catch (RuntimeException exp) {
            throw new RemittanceAppException(ErrorRef.GENERAL_ERROR, ResponseRef.SERVER_ERROR, exp.getMessage(), exp);
        }
    }

    public PageVO<PartnerVO> findAllPartners(Integer pageNumber, Integer pageSize, String[] sortBy, Sort.Direction order,
                                             PartnerVO vo) {
        Page<PartnerVO> page = super.findAll(modelMapper.map(vo, PartnerEO.class), null, pageNumber, pageSize, sortBy, order)
                .map(eo -> modelMapper.map(eo, PartnerVO.class));
        return new PageVO<>(page.getContent(), page.isFirst(), page.hasPrevious(), page.getNumber(), page.hasNext(), page.isLast(), page.getSize(), page.getNumberOfElements(), page.getTotalElements(), page.getTotalPages(), page.isEmpty());
    }

    public void deleteTradingPartnerById(Long id) {
        try {
            if (super.existsById(id)) {
                super.deleteById(id);
            } else
                throw new RemittanceAppException(ErrorRef.PARTNER_NOT_FOUND, ResponseRef.NOT_FOUND, MessageTranslator.toLocale("TradingPartner.EntryNotFound", new Object[]{id}));
        } catch (RuntimeException exp) {
            throw new RemittanceAppException(ErrorRef.GENERAL_ERROR, ResponseRef.BAD_REQUEST, exp.getMessage(), exp);
        }
    }
}