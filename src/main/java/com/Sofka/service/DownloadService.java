package com.Sofka.service;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Sofka.domain.Download;
import com.Sofka.repository.DownloadRepository;
import com.Sofka.service.Interface.Idownload;

@Service
public class DownloadService implements Idownload{

	@Autowired
	private DownloadRepository downloadrepository;
	
	@Override
	public List<Download> getIDownload() {
		return downloadrepository.findAll();
	}

	@Override
	public Download savedownload(Download download) {
		download.setCreatedAt(Instant.now());
		return downloadrepository.save(download);
	}

	@Override
	public Download updatedowload(Integer id, Download download) {
		download.setId(id);
		download.setCreatedAt(Instant.now());
		return downloadrepository.save(download);
	}

	@Override
	public Download deletedowload(Integer id) {
		var descarga=downloadrepository.findById(id);
		if(descarga.isPresent()) {
			downloadrepository.delete(descarga.get());
			return descarga.get();
		}else {
			return null;
		}
	}

}
