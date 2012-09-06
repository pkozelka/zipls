all: clean package

clean:
	rm -rf target/

package:
	mkdir --parent target/classes
	javac -d target/classes -sourcepath src `find * -name '*.java'`
	jar cvfe target/zipls.jar net.kozelka.zip.ZipDeepLister -C target/classes/ .

run:
	java -jar target/zipls.jar test/sample.zip
