package com.nttdata.msbootcoin.service.impl;

import com.nttdata.msbootcoin.model.BootcoinTransaction;
import com.nttdata.msbootcoin.model.BootcoinWallet;
import com.nttdata.msbootcoin.model.MessageDto;
import com.nttdata.msbootcoin.repository.BootcoinTransactionRepository;
import com.nttdata.msbootcoin.repository.BootcoinWalletRepository;
import com.nttdata.msbootcoin.service.BootcoinWalletService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BootcoinWalletServiceImpl implements BootcoinWalletService {
	
	private final BootcoinWalletRepository walletRepository;
	private final BootcoinTransactionRepository transactionRepo;
	private final KafkaTemplate<String, MessageDto> kafkaTemplate;
	
	@Override
	public Flux<BootcoinWallet> findAll() {
		return walletRepository.findAll();
	}

	@Override
	public Mono<BootcoinWallet> findById(String id) {
		return walletRepository.findById(id);
	}

	@Override
	public Mono<BootcoinWallet> findByPhoneNumber(String phoneNumber) {
		return walletRepository.findByPhoneNumber(phoneNumber);
	}

	@Override
	public Mono<BootcoinWallet> save(BootcoinWallet wallet) {
		return walletRepository.save(wallet);
	}

	@Override
	public void deleteById(String id) {
		walletRepository.deleteById(id).subscribe();
	}

	@Override
	public void bootcoinBuyRequest(MessageDto message) {
		kafkaTemplate.send("bootcoinBuyRequest", message);
	}



}
