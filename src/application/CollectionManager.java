package application;

import object_of_collection.MusicBand;
import object_of_collection.MusicGenre;
import building_procedure.*;


import java.io.File;
import java.util.Collections;
import java.util.Date;
import java.util.Vector;

public class CollectionManager {

    private BandBuilder bandBuilder;
    private Date initialisationDate;
    //alt+enter
    private Vector<MusicBand> vector;

    public CollectionManager(File file) {
    }

    public CollectionManager() {
        this.bandBuilder = new BandBuilder();
        this.initialisationDate = new Date();
        vector = new Vector<MusicBand>();
    }

    void info() {
        System.out.println("Коллекция Vector хранит обьекты музыкальных групп Application.MusicBand");
        System.out.println("Коллекция была проинициализирована: " + initialisationDate);
        System.out.println("Коллекция содержит: " + vector.size() + " элементов");
    }

    void help() {
        System.out.println("Доступные команды" +
                "help : вывести справку по доступным командам\n" +
                "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "add {element} : добавить новый элемент в коллекцию\n" +
                "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                "remove_by_id id : удалить элемент из коллекции по его id\n" +
                "clear : очистить коллекцию\n" +
                "save : сохранить коллекцию в файл\n" +
                "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                "exit : завершить программу (без сохранения в файл)\n" +
                "insert_at index {element} : добавить новый элемент в заданную позицию\n" +
                "remove_first : удалить первый элемент из коллекции\n" +
                "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный\n" +
                "count_less_than_genre genre : вывести количество элементов, значение поля genre которых меньше заданного\n" +
                "filter_starts_with_name name : вывести элементы, значение поля name которых начинается с заданной подстроки\n" +
                "print_field_ascending_label : вывести значения поля label всех элементов в порядке возрастания");
    }

    void clear() {
        vector.removeAllElements();
        System.out.println("Коллекция была очищена.");
    }

    void save() {
        System.out.println("Комманда save была вызвана");
    }//

    void removeFirst() {
        if (vector.isEmpty()) System.out.println("Коллекция пуста!");
        else {
            vector.remove(vector.firstElement());
            System.out.println("Первый элемент коллекции был удален");
        }
    }

    void exit() {
        System.exit(0);
    }

    void show() {
        System.out.println("Комманда show была вызвана");
        for (MusicBand band : vector) System.out.println("Все элементы коллекции в строковом представлении: "+band.toString());
    }

    // TODO: 31.10.2020
    void add(MusicBand musicBand) {
        vector.add(bandBuilder.create());
    }

    void printFieldAscendingLabel() {
        Collections.sort(vector);
        for (MusicBand band : vector) {
            System.out.println(band.getLabel().getName());
        }
        System.out.println("Значения поля label всех элементов в порядке возрастания были выведены");
    }

    void removeById(Long key) {
        if (vector.contains(key)) {
            vector.remove(key);
            System.out.println("Элемент " + key + " удален");
        } else System.out.println("Такого элемента нет в коллекции");
    }

    // TODO: 31.10.2020
    void updateId(Long id, MusicBand musicBand) {
        for (int i = 0; i < vector.size(); i++) {
            if (id.equals(musicBand.getId())) {
                vector.set(i, musicBand);
                return;
            }
        }
        System.out.println("Значения не совпадают между собой.");
    }

    // TODO: 31.10.2020
    void insertAtIndex(Integer index, MusicBand musicBand) {
        vector.insertElementAt(musicBand, index);
        System.out.println("Элемент " + musicBand + " был добавлен в позицию " + index + " коллекции");
    }

    // TODO: 31.10.2020
    void removeLower(MusicBand musicBand) {
        vector.removeIf(band -> band.compareTo(musicBand) < 0);
    }

    // TODO: 31.10.2020
    void filterStartsWithName(String name) {
        for (MusicBand band : vector) {
            if (band.getName() == name) System.out.println(band.getName() + System.lineSeparator());
            else return;
        }
        System.out.println("Элементов, значение поля name которых начинается с подстроки: " + name + " нет в коллекции");
    }

    // TODO: 31.10.2020
    void coutLessThanGanre(String name) {//вывести количество элементов, значение поля genre которых меньше заданного
        int k = 0;
        try {
            MusicGenre musicGenre = MusicGenre.valueOf(name);
            for (MusicBand band : vector) {
                if (musicGenre.ordinal() < band.getGenre().ordinal()) k++;
            }
            System.out.println("Колличество элементов, значение поля: " + k);
        } catch (IllegalArgumentException e) {
            System.out.println("Такого жанра не существует");
        }

    }

    // TODO: 31.10.2020
    void executeScript() {

    }
}
