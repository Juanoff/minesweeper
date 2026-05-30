package ru.shift.controller;

import ru.shift.records.RecordsService;
import ru.shift.view.RecordNameListener;

public class RecordsController implements RecordNameListener {
    private final RecordsService recordsService;

    public RecordsController(RecordsService recordsService) {
        this.recordsService = recordsService;
    }

    @Override
    public void onRecordNameEntered(String name) {
        recordsService.addNewRecord(name);
    }
}
