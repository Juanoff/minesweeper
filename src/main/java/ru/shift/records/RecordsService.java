package ru.shift.records;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.shift.model.GameModelListener;
import ru.shift.model.field.cell.ICell;
import ru.shift.shared.GameType;
import ru.shift.timer.TimerListener;

public class RecordsService implements GameModelListener, TimerListener {
    private static final Logger log = LoggerFactory.getLogger(RecordsService.class);

    private static final String RECORDS_FILE = "records.json";
    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    private static final OpenOption[] openOptions = new OpenOption[]{
            StandardOpenOption.CREATE,
            StandardOpenOption.TRUNCATE_EXISTING,
            StandardOpenOption.WRITE
    };

    private static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .disableHtmlEscaping()
            .create();

    private GameType winGameType;
    private int winnerTime;

    private final Map<GameType, RecordEntry> records = new EnumMap<>(GameType.class);
    private final List<RecordsServiceListener> listeners = new ArrayList<>();

    public void addListener(RecordsServiceListener listener) {
        listeners.add(listener);
    }

    public void setRecord(GameType type, RecordEntry entry) {
        records.put(type, entry);
        save();
    }

    public void addNewRecord(String winnerName) {
        if (!isNewRecord(winGameType, winnerTime)) {
            return;
        }

        RecordEntry newRecord = new RecordEntry(winnerName, winnerTime);
        setRecord(winGameType, newRecord);
        notifyNewRecord(winGameType, newRecord);
    }

    public void notifyNewRecord(GameType type, RecordEntry newRecord) {
        for (RecordsServiceListener l : List.copyOf(listeners)) {
            l.onNewGameRecord(type, newRecord);
        }
    }

    public boolean isNewRecord(GameType type, int newTime) {
        RecordEntry current = records.get(type);
        return current == null || newTime < current.time();
    }

    public void load() {
        Path path = Paths.get(RECORDS_FILE);
        if (!Files.exists(path)) {
            return;
        }

        try (Reader reader = Files.newBufferedReader(path, DEFAULT_CHARSET)) {
            Type type = TypeToken.getParameterized(Map.class, GameType.class, RecordEntry.class).getType();
            Map<GameType, RecordEntry> data = gson.fromJson(reader, type);
            if (data != null) {
                records.putAll(data);
                notifyLoadRecords(records);
            }
        } catch (Exception e) {
            log.error("Failed to load records from {}: {}", path.toAbsolutePath(), e.getMessage());
        }
    }

    public void notifyLoadRecords(Map<GameType, RecordEntry> prevRecords) {
        for (RecordsServiceListener l : listeners) {
            l.onLoadRecords(prevRecords);
        }
    }

    private void save() {
        Path path = Paths.get(RECORDS_FILE);

        try (Writer writer = Files.newBufferedWriter(path, DEFAULT_CHARSET, openOptions)) {
            if (Files.exists(path)) {
                Path backup = Paths.get(RECORDS_FILE + ".bak");
                Files.copy(path, backup, StandardCopyOption.REPLACE_EXISTING);
            }

            gson.toJson(records, writer);
        } catch (Exception e) {
            log.error("Failed to save records to {}: {}", path.toAbsolutePath(), e.getMessage());
        }
    }

    @Override
    public void onGameWin(GameType gameType) {
        this.winGameType = gameType;
    }

    @Override
    public void onTimerTick(int curTime) {
        this.winnerTime = curTime;
    }

    @Override
    public void onCellOpened(int x, int y, ICell cell) {

    }

    @Override
    public void onCellFlagToggled(int x, int y, ICell cell) {

    }

    @Override
    public void onGameStart() {

    }

    @Override
    public void onGameRestart(GameType gameType) {

    }

    @Override
    public void onGameExit() {

    }

    @Override
    public void onGameLose() {

    }

    @Override
    public void onMinesLeftChanged(int minesLeft) {

    }

    public record RecordEntry(String winnerName, int time) {
    }
}
