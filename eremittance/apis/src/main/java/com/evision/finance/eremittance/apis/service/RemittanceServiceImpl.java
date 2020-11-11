package com.evision.finance.eremittance.apis.service;

import com.evision.finance.eremittance.apis.entity.RemittanceEO;
import com.evision.finance.eremittance.authenticator.entity.PageVO;
import com.evision.finance.eremittance.authenticator.entity.enums.ErrorRef;
import com.evision.finance.eremittance.authenticator.entity.enums.ResponseRef;
import com.evision.finance.eremittance.apis.repository.RemittanceRepo;
import com.evision.finance.eremittance.apis.view.RemittanceVO;
import com.evision.finance.eremittance.authenticator.service.BasicAbstractService;
import com.evision.finance.eremittance.authenticator.util.MessageTranslator;
import com.evision.finance.eremittance.authenticator.util.RemittanceAppException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RemittanceServiceImpl extends BasicAbstractService<RemittanceEO> {

    protected RemittanceServiceImpl(ModelMapper modelMapper, JpaRepository<RemittanceEO, Long> jpaRepository) {
        super(modelMapper, jpaRepository);
    }

    public RemittanceVO createRemittance(RemittanceVO vo) throws RemittanceAppException {
        if (!jpaRepository.existsById(vo.getId())) {
            return modelMapper.map(super.save(modelMapper.map(vo, RemittanceEO.class)), RemittanceVO.class);
        } else
            throw new RemittanceAppException(ErrorRef.USER_ALREADY_EXIST, ResponseRef.BAD_REQUEST, MessageTranslator
                    .toLocale("User.EntryAlreadyExists", new Object[]{vo.getId(), vo.getEmailAddress()}));
    }


    public void createRemittanceBulk(List<RemittanceVO> vos) {
        super.saveAll(vos.stream().map(vo -> modelMapper.map(vo, RemittanceEO.class)).collect(Collectors.toList()));
    }

    public RemittanceVO findRemittanceById(Long id) {
        return modelMapper.map(Optional.ofNullable(super.findById(id)).orElseThrow(
                () -> new RemittanceAppException(ErrorRef.USER_NOT_FOUND, ResponseRef.NOT_FOUND,
                                                 MessageTranslator.toLocale("User.EntryNotFound", new Object[]{id}))),
                               RemittanceVO.class);
    }

    public PageVO<RemittanceVO> findAllRemittances(Integer pageNumber, Integer pageSize, String[] sortBy,
                                                   Sort.Direction order,
                                                   RemittanceVO vo) {
        Page<RemittanceVO> page = super
                .findAll(modelMapper.map(vo, RemittanceEO.class), null, pageNumber, pageSize, sortBy, order)
                .map(eo -> modelMapper.map(eo, RemittanceVO.class));
        return new PageVO<>(page.getContent(), page.isFirst(), page.hasPrevious(), page.getNumber(),
                            page.hasNext(), page.isLast(), page.getSize(), page.getNumberOfElements(),
                            page.getTotalElements(), page.getTotalPages(), page.isEmpty());
    }

    public RemittanceVO findRemittance(RemittanceVO vo) {
        return modelMapper.map(Optional.ofNullable(super.findOne(modelMapper.map(vo, RemittanceEO.class), null))
                                       .orElseThrow(() -> new RemittanceAppException(ErrorRef.USER_NOT_FOUND,
                                                                                     ResponseRef.NOT_FOUND,
                                                                                     MessageTranslator.toLocale(
                                                                                             "Remittance.EntryNotFound",
                                                                                             new Object[]{vo.getId()}))),
                               RemittanceVO.class);
    }

    public void updateRemittance(RemittanceVO vo) {
        if (super.findById(vo.getId()) != null) {
            jpaRepository.
                    save(modelMapper.map(vo, RemittanceEO.class));
        } else
            throw new RemittanceAppException(ErrorRef.USER_NOT_FOUND, ResponseRef.BAD_REQUEST,
                                             MessageTranslator.toLocale("Remittance.EntryNotFound",
                                                                        new Object[]{vo.getId()}));
    }

    public void deleteRemittanceById(Long id) {
        if (super.existsById(id)) {
            jpaRepository.deleteById(id);
        } else {
            throw new RemittanceAppException(ErrorRef.USER_NOT_FOUND, ResponseRef.BAD_REQUEST,
                                             MessageTranslator.toLocale("Remittance.EntryNotFound", new Object[]{id}));
        }
    }


}
