package ru.shift.records;

import java.util.Map;
import ru.shift.shared.GameType;

public interface RecordsServiceListener {
    void onNewGameRecord(GameType type, RecordsService.RecordEntry newRecord);

    void onLoadRecords(Map<GameType, RecordsService.RecordEntry> prevRecords);
}
