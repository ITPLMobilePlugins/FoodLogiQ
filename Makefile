IMAGE_NAME:=foodlogiq
IMAGE_TAG:=latest

build:
	mvn package -Dmaven.test.skip
build-with-testing:
	mvn package
image:
	docker build -f Dockerfile -t $(IMAGE_NAME):$(IMAGE_TAG) .
run:
	docker run -i --rm -p 8080:8080 $(IMAGE_NAME):$(IMAGE_TAG)
