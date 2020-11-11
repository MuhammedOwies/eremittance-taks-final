package com.evision.finance.eremittance.authenticator.service;

import com.evision.finance.eremittance.authenticator.entity.PageVO;
import com.evision.finance.eremittance.authenticator.entity.SysUserEO;
import com.evision.finance.eremittance.authenticator.entity.UserVO;
import com.evision.finance.eremittance.authenticator.entity.enums.ErrorRef;
import com.evision.finance.eremittance.authenticator.entity.enums.ResponseRef;
import com.evision.finance.eremittance.authenticator.repository.UserRepoImpl;
import com.evision.finance.eremittance.authenticator.util.MessageTranslator;
import com.evision.finance.eremittance.authenticator.util.RemittanceAppException;
import com.evision.finance.eremittance.authenticator.util.Utils;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
//@Transactional
public class UserServiceImpl extends BasicAbstractService<SysUserEO> {
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(ModelMapper modelMapper, JpaRepository<SysUserEO, Long> jpaRepository,
                           @Lazy PasswordEncoder passwordEncoder) {
        super(modelMapper, jpaRepository);
        this.passwordEncoder = passwordEncoder;
    }

    public UserVO createUser(UserVO vo) {
        if (!((UserRepoImpl) jpaRepository).existsByUsernameOrEmail(vo.getPrincipal(), vo.getEmailAddress())) {
            return modelMapper.map(super.save(modelMapper.map(vo, SysUserEO.class)), UserVO.class);
        } else
            throw new RemittanceAppException(ErrorRef.USER_ALREADY_EXIST, ResponseRef.BAD_REQUEST,
                                             MessageTranslator.toLocale("User.EntryAlreadyExists",
                                                                        new Object[]{vo.getPrincipal(),
                                                                                vo.getEmailAddress()}));
    }

    public void saveAllUsers(List<UserVO> vos) {
        super.saveAll(vos.stream().map(vo -> modelMapper.map(vo, SysUserEO.class)).collect(Collectors.toList()));
    }

    public UserVO getUserById(Long id) {
        return modelMapper.map(Optional.ofNullable(super.findById(id))
                                       .orElseThrow(() -> new RemittanceAppException(ErrorRef.USER_NOT_FOUND,
                                                                                     ResponseRef.NOT_FOUND,
                                                                                     MessageTranslator.toLocale(
                                                                                             "User.EntryNotFound",
                                                                                             new Object[]{id}))),
                               UserVO.class);
    }

    public PageVO<UserVO> findAllUsers(Integer pageNumber, Integer pageSize, String[] sortBy, Sort.Direction order,
                                       UserVO vo) {
        Page<UserVO> page = super.findAll(modelMapper.map(vo, SysUserEO.class), null, pageNumber, pageSize, sortBy,
                                          order)
                .map(eo -> modelMapper.map(eo, UserVO.class));
        return new PageVO<>(page.getContent(), page.isFirst(), page.hasPrevious(), page.getNumber(), page.hasNext(),
                            page.isLast(), page.getSize(), page.getNumberOfElements(), page.getTotalElements(),
                            page.getTotalPages(), page.isEmpty());
    }

    public UserVO findUser(UserVO vo) {
        return modelMapper.map(Optional.ofNullable(super.findOne(modelMapper.map(vo, SysUserEO.class), null))
                                       .orElseThrow(() -> new RemittanceAppException(ErrorRef.USER_NOT_FOUND,
                                                                                     ResponseRef.NOT_FOUND,
                                                                                     MessageTranslator.toLocale(
                                                                                             "User.EntryNotFound",
                                                                                             new Object[]{vo.getPrincipal()}))),
                               UserVO.class);
    }

    public void updateUser(UserVO vo) {
        if (super.findById(vo.getId()) != null) {
            ((UserRepoImpl) jpaRepository).updateUserEO(vo.getDisplayName(), vo.getEmailAddress(), vo.getStatus(), Utils
                    .getPrincipal(), vo.getId());
        }
        throw new RemittanceAppException(ErrorRef.USER_NOT_FOUND, ResponseRef.BAD_REQUEST,
                                         MessageTranslator.toLocale("User.EntryNotFound", new Object[]{vo.getId()}));
    }

    public void deleteUserById(Long id) {
        if (super.existsById(id)) {
            ((UserRepoImpl) jpaRepository).removeUserEO(Utils.getPrincipal(), id);
        } else {
            throw new RemittanceAppException(ErrorRef.USER_NOT_FOUND, ResponseRef.BAD_REQUEST,
                                             MessageTranslator.toLocale("User.EntryNotFound", new Object[]{id}));
        }
    }

//    public void changeUserPassword(UserVO userVO, PasswordChangeVO vo) {
//        if (passwordEncoder.matches(vo.getOldCredential(), userVO.getCredentials())) {
//            ((UserRepoImpl) jpaRepository).updateUserEOCredentials(vo.getNewCredential(), Utils.getPrincipal(),
//            userVO.getId());
//        } else
//            throw new RemittanceAppException(ErrorRef.USER_INCORRECT_OLD_PASSWORD, ResponseRef.BAD_REQUEST,
//            MessageTranslator.toLocale("User.IncorrectOldPassword", null));
//    }

    public void resetUserPassword(Long id, String newCredentials) {
        if (super.findById(id) != null) {
            ((UserRepoImpl) jpaRepository).updateUserEOCredentials(newCredentials, Utils.getPrincipal(), id);
        } else
            throw new RemittanceAppException(ErrorRef.USER_NOT_FOUND, ResponseRef.BAD_REQUEST,
                                             MessageTranslator.toLocale("User.EntryNotFound", new Object[]{id}));
    }
}